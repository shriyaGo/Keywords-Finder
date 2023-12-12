# Keywords-Finder

**Problem description:** \
Reads text data and search for given list of words in it and returns the matching lines with n lines of leading and trailing context.

Write a function that accepts three parameters:
1. The input content.
2. The array of search string to search for.
3. The number of leading and trailing lines to add in output object.

**Returns** \
A map with searchKeywords as key and value as an array of matching lines with noOfLines leading and trailing context lines.

**Function Signature** \
Map<String, List<String>> findOccurences(String content, List<String> searchKeywords, int noOfLines);

**Sample Input** 
```
content: "Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n"
          + "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an"
          + " unknown printer took a galley of type and scrambled it to make a type specimen"
          + " book.\n"
          + "It has survived not only five centuries, but also the leap into electronic"
          + " typesetting, remaining essentially unchanged. It was popularized in the 1960s with"
          + " the release of Letraset sheets containing Lorem Ipsum passages, and more recently"
          + " with desktop publishing software like Aldus PageMaker including versions of Lorem"
          + " Ipsum.\n"
searchKeywords: ["Lorem", "1960"]
noOfLines: 0
```

**Sample Output**
```
{
    "1960" : ["It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularized in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.], 
    "Lorem" : ["Lorem Ipsum is simply dummy text of the printing and typesetting industry.", 
               "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", 
               "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularized in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."]
}
```
