# simplehtml

[![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/hyperium/hyper/master/LICENSE)

<b><i>This project is still in developement.</i></b>

Simple Java-Framework that helps you create HTML in Java.

# Examples

<h5>Creating simple HTML with an autoclosing HtmlBuilder</h5>

```Java 
public void main() {
    HtmlBuilder builder = HtmlBuilder.autoClosing();
    builder.html()
               .body()
                   .bold()
                       .text("Simple HTML") // adds content
                    .br()
                    .text("Very simple");
    String asString = builder.toString();
}
```

<h5>Creating simple HTML with an nonAutoclosing HtmlBuilder</h5>
```Java 
public void main() {
    HtmlBuilder builder = HtmlBuilder.nonAutoClosing();
    builder.html()
               .body()
                   .bold()
                       .text("Simple HTML")
                    ._bold() // closes the bold tag
                    .br() // never needs to be closed
                    .text("Very simple")
               ._body()
           .html();
    String asString = builder.toString();
}
```



# License

This project is licensed under the terms of the MIT license.
