package main.java;

import static java.util.Arrays.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public final class LogCrawler {

  /**
   * Problem description: Reads text data and search for given list of words in it and returns the
   * matching lines with n lines of leading and trailing context.
   *
   * <p>Returns a map with searchKeywords as key and value as an array of matching lines with
   * noOfLines leading and trailing context lines.
   *
   * @param content The input content.
   * @param searchKeywords The array of search string to search for.
   * @param noOfLines The number of leading and trailing lines to add in output object.
   * @return Map of string search key to array of matching lines.
   */
  public static Map<String, List<String>> findOccurences(
      String content, List<String> searchKeywords, int noOfLines) {
    String[] lines = content.split("\n");

    TreeMap<String, List<String>> resultMap = new TreeMap<>();
    searchKeywords.stream().sorted().forEach(keyword -> resultMap.put(keyword, new ArrayList<>()));

    for (int lineNum = 0; lineNum < lines.length; lineNum++) {
      for (String keyword : searchKeywords) {
        if (lines[lineNum].toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT))) {
          int startIndex = lineNum < noOfLines ? 0 : lineNum - noOfLines;
          int endIndex =
              (lineNum + noOfLines) >= lines.length ? lines.length - 1 : (lineNum + noOfLines);
          String matchedResult =
              stream(lines, startIndex, endIndex + 1).collect(Collectors.joining("\n"));
          resultMap.get(keyword).add(matchedResult);
        }
      }
    }
    return resultMap;
  }

  private LogCrawler() {
  }
}