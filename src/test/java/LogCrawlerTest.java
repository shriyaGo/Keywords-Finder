package test.java;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import main.java.LogCrawler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for {@link LogCrawler}.
 */
@RunWith(JUnit4.class)
public class LogCrawlerTest {

  private static final String CONTENT =
      "Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n"
          + "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an"
          + " unknown printer took a galley of type and scrambled it to make a type specimen"
          + " book.\n"
          + "It has survived not only five centuries, but also the leap into electronic"
          + " typesetting, remaining essentially unchanged. It was popularized in the 1960s with"
          + " the release of Letraset sheets containing Lorem Ipsum passages, and more recently"
          + " with desktop publishing software like Aldus PageMaker including versions of Lorem"
          + " Ipsum.\n"
          + "1960 was the year lorem ipsum was generated and\n"
          + "in 1961 that is after 1 year of 1960 Lorem ipsum got its popularity\n";

  @Test
  public void findOccurences_withEmptyKeyword_returnsEmptyList() {
    // Act.
    Map<String, List<String>> result =
        LogCrawler.findOccurences(
            CONTENT, /* searchKeywords= */ ImmutableList.of(), /* noOfLines= */ 2);
    // Assert.
    assertThat(result).isEmpty();
  }

  @Test
  public void findOccurences_withZeroOutputContextLine_returnsOnlyMatchingResult() {
    // Act.
    Map<String, List<String>> result =
        LogCrawler.findOccurences(
            CONTENT, /* searchKeywords= */ Arrays.asList("Lorem", "1960"), /* noOfLines= */ 0);
    // Assert.
    assertThat(result)
        .containsExactly(
            "1960",
            Arrays.asList(
                "It has survived not only five centuries, but also the leap into electronic"
                    + " typesetting, remaining essentially unchanged. It was popularized in the"
                    + " 1960s with the release of Letraset sheets containing Lorem Ipsum"
                    + " passages, and more recently with desktop publishing software like Aldus"
                    + " PageMaker including versions of Lorem Ipsum.",
                "1960 was the year lorem ipsum was generated and",
                "in 1961 that is after 1 year of 1960 Lorem ipsum got its popularity"),
            "Lorem",
            Arrays.asList(
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,"
                    + " when an unknown printer took a galley of type and scrambled it to make"
                    + " a type specimen book.",
                "It has survived not only five centuries, but also the leap into electronic"
                    + " typesetting, remaining essentially unchanged. It was popularized in the"
                    + " 1960s with the release of Letraset sheets containing Lorem Ipsum"
                    + " passages, and more recently with desktop publishing software like Aldus"
                    + " PageMaker including versions of Lorem Ipsum.",
                "1960 was the year lorem ipsum was generated and",
                "in 1961 that is after 1 year of 1960 Lorem ipsum got its popularity"));
  }

  @Test
  public void findOccurences_withUnmatchableKeywords_returnsEmptyList() {
    // Act.
    Map<String, List<String>> result =
        LogCrawler.findOccurences(
            CONTENT, /* searchKeywords= */ Arrays.asList("Google", "keyword"), /* noOfLines= */ 0);
    // Assert.
    assertThat(result).containsExactly("Google", ImmutableList.of(), "keyword", ImmutableList.of());
  }

  @Test
  public void findOccurences_withSearchKeyword_returnsMatchingResult() {
    // Act.
    Map<String, List<String>> result =
        LogCrawler.findOccurences(
            CONTENT, /* searchKeywords= */ Arrays.asList("Lorem"), /* noOfLines= */ 1);
    // Assert.
    assertThat(result)
        .containsExactly(
            "Lorem",
            Arrays.asList(
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n"
                    + "Lorem Ipsum has been the industry's standard dummy text ever since the"
                    + " 1500s, when an unknown printer took a galley of type and scrambled it to"
                    + " make a type specimen book.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n"
                    + "Lorem Ipsum has been the industry's standard dummy text ever since the"
                    + " 1500s, when an unknown printer took a galley of type and scrambled it to"
                    + " make a type specimen book.\n"
                    + "It has survived not only five centuries, but also the leap into electronic"
                    + " typesetting, remaining essentially unchanged. It was popularized in the"
                    + " 1960s with the release of Letraset sheets containing Lorem Ipsum passages,"
                    + " and more recently with desktop publishing software like Aldus PageMaker"
                    + " including versions of Lorem Ipsum.",
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when"
                    + " an unknown printer took a galley of type and scrambled it to make a type"
                    + " specimen book.\n"
                    + "It has survived not only five centuries, but also the leap into electronic"
                    + " typesetting, remaining essentially unchanged. It was popularized in the"
                    + " 1960s with the release of Letraset sheets containing Lorem Ipsum passages,"
                    + " and more recently with desktop publishing software like Aldus PageMaker"
                    + " including versions of Lorem Ipsum.\n"
                    + "1960 was the year lorem ipsum was generated and",
                "It has survived not only five centuries, but also the leap into electronic"
                    + " typesetting, remaining essentially unchanged. It was popularized in the"
                    + " 1960s with the release of Letraset sheets containing Lorem Ipsum passages,"
                    + " and more recently with desktop publishing software like Aldus PageMaker"
                    + " including versions of Lorem Ipsum.\n"
                    + "1960 was the year lorem ipsum was generated and\n"
                    + "in 1961 that is after 1 year of 1960 Lorem ipsum got its popularity",
                "1960 was the year lorem ipsum was generated and\n"
                    + "in 1961 that is after 1 year of 1960 Lorem ipsum got its popularity"));
  }
}