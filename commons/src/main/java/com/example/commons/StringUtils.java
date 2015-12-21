/*
 *     Copyright (c) 2015-present Victor Hidalgo Lorenzo
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.example.commons;

import android.text.Html;
import android.text.Spanned;
import java.util.regex.Pattern;

import static com.example.commons.Utils.isNotBlank;

public class StringUtils {

  public static final String HTML_BR_TAG;
  public static final String HTML_LI_TAG_UNICODE_REPLACEMENT;
  public static final String HTML_LI_TAG_REGEX;
  public static final Pattern HTML_LI_TAG_PATTERN;
  public static final Pattern LINE_SEPARATOR_PATTERN;

  static {
    HTML_BR_TAG = "<br/>";
    HTML_LI_TAG_UNICODE_REPLACEMENT = "\\n&#149;";
    HTML_LI_TAG_REGEX = "<\\s*li\\s*>";
    HTML_LI_TAG_PATTERN = Pattern.compile(HTML_LI_TAG_REGEX, Pattern.CASE_INSENSITIVE);
    LINE_SEPARATOR_PATTERN = Pattern.compile(System.getProperty("line.separator"), Pattern.CASE_INSENSITIVE);
  }

  private StringUtils() {
  }

  private static String safeString(String source) {
    return isNotBlank(source) ? source : "";
  }

  public static Spanned fromHtml(String source) {
    return Html.fromHtml(replaceLineSeparatorWithBRtag(replaceLItagWithUnicode(safeString(source))));
  }

  public static String replaceLineSeparatorWithBRtag(String input) {
    return input == null ? "" : LINE_SEPARATOR_PATTERN.matcher(input).replaceAll(HTML_BR_TAG);
  }

  public static String replaceLItagWithUnicode(String input) {
    return input == null ? "" : HTML_LI_TAG_PATTERN.matcher(input).replaceAll(HTML_LI_TAG_UNICODE_REPLACEMENT);
  }
}
