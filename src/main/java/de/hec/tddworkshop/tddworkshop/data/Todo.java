package de.hec.tddworkshop.tddworkshop.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Todo {

  private int id;
  private Category category;
  private String description;


  @Data
  @Builder
  public static class Category {
    private final String name;
  }
}
