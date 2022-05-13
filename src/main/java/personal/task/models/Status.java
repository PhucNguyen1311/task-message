package personal.task.models;

public class Status {
    private String name;
    private Integer value;
    private String category;
    private String description;
    private String helpUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHelpUrl() {
        return helpUrl;
    }

    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    @Override
    public String toString() {
        return "Status{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", helpUrl='" + helpUrl + '\'' +
                '}';
    }
}
