package org.eoghancorp.tracker.Models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;

public class FoodResponse {
    private String text;
    private Parsed[] parsed;
    private Hint[] hints;
    private Link links;

    public void LoopHints(FoodResponse res) {
        try
        {
            ObjectMapper mapper = new ObjectMapper();

            for(Hint hint : res.hints) {
                Nutrients nuuts = hint.getFood().getNutrients();
                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(nuuts);
                // System.out.println(json);
                if(nuuts.getSUGAR() != null) {
                }

            }


        }
        catch(JsonProcessingException e) {
            System.out.println(e.getMessage());
        }


    }

    public Parsed[] getParsed() {
        return parsed;
    }

    public Hint[] getHints() {
        return hints;
    }

    public Link getLinks() {
        return links;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setParsed(Parsed[] parsed) {
        this.parsed = parsed;
    }

    @JsonProperty("_links")
    public void setLinks(Link links) {
        this.links = links;
    }

    public void setHints(Hint[] hints) {
        this.hints = hints;
    }

}



class Parsed {
    private Food food;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}



class Food {
    private String foodId;
    private String label;
    private Nutrients nutrients;
    private String category;
    private String categoryLabel;
    private String image;
    private String brand;
    private String foodContentsLabel;
    private ServingSize[] servingSizes;
    private double servingsPerContainer;

    public double getServingsPerContainer() {
        return servingsPerContainer;
    }

    public String getBrand() {
        return brand;
    }

    public String getFoodContentsLabel() {
        return foodContentsLabel;
    }

    public ServingSize[] getServingSizes() {
        return servingSizes;
    }

    public Nutrients getNutrients() {
        return nutrients;
    }

    public String getCategory() {
        return category;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public String getFoodId() {
        return foodId;
    }

    public String getImage() {
        return image;
    }

    public String getLabel() {
        return label;
    }

    @JsonProperty("servingSizes")
    public void setServingSizes(ServingSize[] servingSizes) {
        this.servingSizes = servingSizes;
    }

    public void setServingsPerContainer(double servingsPerContainer) {
        this.servingsPerContainer = servingsPerContainer;
    }

    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setFoodContentsLabel(String foodContentsLabel) {
        this.foodContentsLabel = foodContentsLabel;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}

class ServingSize {
    private String uri;
    private String label;
    private double quantity;

    public double getQuantity() {
        return quantity;
    }

    public String getLabel() {
        return label;
    }

    public String getUri() {
        return uri;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

@Getter
@Setter
class Nutrients {

    @JsonProperty("CA")
    private String CA;         // Calcium (mg)
    @JsonProperty("ENERC_KCAL")
    private String ENERC_KCAL; // Energy (kcal)
    @JsonProperty("PROCNT")
    private String PROCNT;     // Protein (g)
    @JsonProperty("FAT")
    private String FAT;        // Fat (g)
    @JsonProperty("FAMS")
    private String FAMS;       // Monounsaturated Fatty Acids (g)
    @JsonProperty("FAPU")
    private String FAPU;       // Polyunsaturated Fatty Acids (g)
    @JsonProperty("CHOCDF")
    private String CHOCDF;     // Carbohydrates (g)
    @JsonProperty("CHOLE")
    private String CHOLE;      // Cholesterol (mg)
    @JsonProperty("FIBTG")
    private String FIBTG;      // Fiber (g)
    @JsonProperty("FASAT")
    private String FASAT;      // Saturated Fatty Acids (g)
    @JsonProperty("FATRN")
    private String FATRN;      // Total fatty acids (g)
    @JsonProperty("FE")
    private String FE;         // Iron (mg)
    @JsonProperty("FOLDFE")
    private String FOLDFE;     // Folate (µg)
    @JsonProperty("K")
    private String K;          // Potassium (mg)
    @JsonProperty("MG")
    private String MG;         // Magnesium (mg)
    @JsonProperty("NA")
    private String NA;         // Sodium (mg)
    @JsonProperty("P")
    private String P;          // Phosphorus (mg)
    @JsonProperty("RIBF")
    private String RIBF;       // Riboflavin (mg)
    @JsonProperty("SUGAR")
    private String SUGAR;      // Sugar (g)
    @JsonProperty("THIA")
    private String THIA;       // Thiamin (g)
    @JsonProperty("TOCPA")
    private String TOCPA;      // Vitamin E (mg)
    @JsonProperty("VATA_RAE")
    private String VATA_RAE;   // Vitamin A (µg)
    @JsonProperty("VITB12")
    private String VITB12;     // Vitamin B12 (µg)
    @JsonProperty("VITB6A")
    private String VITB6A;     // Vitamin B-6 (mg)
    @JsonProperty("VITC")
    private String VITC;       // Vitamin C (mg)
    @JsonProperty("VITD")
    private String VITD;       // Vitamin D (mg)
    @JsonProperty("VITK1")
    private String VITK1;      // Vitamin K (µg)
    @JsonProperty("ZN")
    private String ZN;          // Zinc (mg)

}

class Hint {
    private Food food;
    private Measure[] measures;

    public Food getFood() {
        return food;
    }

    public Measure[] getMeasures() {
        return measures;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setMeasures(Measure[] measures) {
        this.measures = measures;
    }
}

class Measure {
    private String uri;
    private String label;
    private Qualified[] qualified;

    public String getLabel() {
        return label;
    }

    public Qualified[] getQualified() {
        return qualified;
    }

    public String getUri() {
        return uri;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public void setQualified(Qualified[] qualified) {
        this.qualified = qualified;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}

class Qualified {
    public Qualifiers qualifiers[];

    public Qualifiers[] getQualifiers() {
        return qualifiers;
    }

    public void setQualifiers(Qualifiers[] qualifiers) {
        this.qualifiers = qualifiers;
    }
}


class Qualifiers {
    private String uri;
    private String label;

    public String getLabel() {
        return label;
    }

    public String getUri() {
        return uri;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

class Link {
    private Next next;

    public Next getNext() {
        return next;
    }

    public void setNext(Next next) {
        this.next = next;
    }
}
class Next {
    private String title;
    private String href;

    public String getHref() {
        return href;
    }

    public String getTitle() {
        return title;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
