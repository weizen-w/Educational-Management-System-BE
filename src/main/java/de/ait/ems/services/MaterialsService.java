package de.ait.ems.services;

import de.ait.ems.dto.MaterialDto;
import de.ait.ems.models.Group;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

/**
 * @author Oleksandr Zhurba on 15.11.2023.
 * @project Educational-Management-System-BE
 **/
@RequiredArgsConstructor
@Service
public class MaterialsService {

  private final GroupsService groupsService;
  private static final String LOCAL_FILENAME = "out.md";
  private static Map<String, String> readFileFromUrl(String url) {
    StringBuilder answer = new StringBuilder();
    Map<String, String> output = new HashMap<>();
    try (InputStream in = new URL(url).openStream()) {
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

  public List<MaterialDto> getMaterialsByGroupId(Long groupId) {
    Group group = groupsService.getGroupOrThrow(groupId);
    String link = group.getLinkTemplate();
    boolean leading0 = link.contains("0x0");
    List<MaterialDto> materialDtoList = new ArrayList<>();
    String tempLink;
    int countFailed = 0;
    for (int i = 1; i < 100; i++) {
      String ord;
      String replace;
      if (leading0) {
        if(i<10)
          ord = "0" + i;
        else
          ord = "" + i;
        replace = "0x0";
      } else {
        ord = "" + i;
        replace = "000";
      }
      tempLink = link.replace(replace, ord);
      ord="000"+ord;
      System.out.println(tempLink);
      Map<String, String> output = readFileFromUrl(tempLink);
      if (output.get("code").equals("success")) {
        MaterialDto material = new MaterialDto();
        material.setMdContent(output.get("body"));
        material.setOrder(ord);
        materialDtoList.add(material);
        countFailed=0;
      }else if(!output.get("code").equals("success")) {
        System.out.println(output.get("code"));
        System.out.println(output.get("body"));
        countFailed+=1;
        if (countFailed>10) {
          break;
        }
      }
    }
    return materialDtoList;
  }
}
