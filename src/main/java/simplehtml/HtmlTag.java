
package simplehtml;

import java.util.Optional;

/**
 * Interface that represents a HTML tag. By implementing this interface you can add additional tag´s that are not
 * present from the get go.
 * 
 * @author c.timpert
 */
public interface HtmlTag {

    public String getOpeningTag();

    public Optional<String> getClosingTag();

}
