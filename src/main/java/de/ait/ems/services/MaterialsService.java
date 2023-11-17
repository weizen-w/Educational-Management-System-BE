package de.ait.ems.services;

import de.ait.ems.dto.MaterialDto;
import de.ait.ems.models.Group;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

/**
 * @author Oleksandr Zhurba on 15.11.2023.
 * @project Educational-Management-System-BE
 **/
@RequiredArgsConstructor
@Service
public class MaterialsService {

  private final GroupsService groupsService;

  private static Map<String, String> readFileFromUrl(String url) {
    Map<String, String> output = new HashMap<>();
    try (InputStream in = reencode(url).toURL().openStream()) {
      Scanner scanner = new Scanner(in, StandardCharsets.UTF_8);
      while (scanner.hasNext()) {
        String line = scanner.useDelimiter("\\A").next();
        output.put("body", line);
        output.put("code", "success");
      }
      scanner.close();
    } catch (IOException ex) {
      output.put("body", ex.getMessage());
      output.put("code", "failed");
    }
    return output;
  }

  private static URI reencode(String url) { // best effort
    final String decodedUrl = UriUtils.decode(url, StandardCharsets.UTF_8);
    return UriComponentsBuilder.fromHttpUrl(decodedUrl)
        .encode()
        .build()
        .toUri();
  }

  private static MaterialDto getMaterialDto(Map<String, String> output, String ord) {
    MaterialDto material = new MaterialDto();
    material.setMdContent(output.get("body"));
    material.setOrder(ord);
    return material;
  }

  public List<MaterialDto> getMaterialsByGroupId(Long groupId) {
    Group group = groupsService.getGroupOrThrow(groupId);
    List<MaterialDto> materialDtoList = new ArrayList<>();

    String link = group.getLinkTemplate();
    if (link != null) {
      boolean leading0 = link.contains("0x0");
      String tempLink;
      int countFailed = 0;
      for (int i = 1; i < 100; i++) {
        String replace;
        String ord;
        if (leading0) {
          if (i < 10) {
            ord = "0" + i;
          } else {
            ord = "" + i;
          }
          replace = "0x0";
        } else {
          ord = "" + i;
          replace = "000";
        }
        tempLink = link.replace(replace, ord);
        ord = "000" + ord;
        Map<String, String> output = readFileFromUrl(tempLink);
        if (output.get("code").equals("success")) {
          MaterialDto material = getMaterialDto(output, ord);
          materialDtoList.add(material);
          countFailed = 0;
        } else if (!output.get("code").equals("success")) {
          countFailed += 1;
          if (countFailed > 10) {
            break;
          }
        }
      }
      return materialDtoList;
    }
    return materialDtoList;
  }

}
