package de.ait.ems.services;

import de.ait.ems.dto.MaterialDto;
import de.ait.ems.models.Group;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

  private static final String LOCAL_FILENAME = "out.md";
  private final GroupsService groupsService;

  private static Map<String, String> readFileFromUrl(String url) {
    StringBuilder answer = new StringBuilder();
    Map<String, String> output = new HashMap<>();
    try (InputStream in = reencode(url).toURL().openStream()) {
      Files.copy(in, Paths.get(LOCAL_FILENAME), StandardCopyOption.REPLACE_EXISTING);
      File fileMd = new File(LOCAL_FILENAME);
      if (fileMd.exists()) {
        Scanner scanner = new Scanner(fileMd);
        while (scanner.hasNext()) {
          String line = scanner.nextLine();
          answer.append(line);
          answer.append("\n");
          output.put("body", answer.toString());
          output.put("code", "success");
        }
        scanner.close();
      }
    } catch (IOException e) {
      output.put("body", e.getMessage());
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
