package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tutorials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutorial {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tutorial_generator")
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "published")
  private boolean published;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @ManyToMany(fetch = FetchType.LAZY, cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinTable(name = "tutorial_tags", joinColumns = { @JoinColumn(name = "tutorial_id") }, inverseJoinColumns = {
      @JoinColumn(name = "tag_id") })
  private Set<Tag> tags = new HashSet<>();

  public void addTag(Tag tag) {
    this.tags.add(tag);
    tag.getTutorials().add(this);
  }

  public void removeTag(long tagId) {
    Tag tag = this.tags.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
    if (tag != null) {
      this.tags.remove(tag);
      tag.getTutorials().remove(this);
    }
  }
}
