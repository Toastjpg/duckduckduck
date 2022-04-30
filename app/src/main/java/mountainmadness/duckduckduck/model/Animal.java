package mountainmadness.duckduckduck.model;

public class Animal {
    private String name;
    private String imageName;
    private String soundName;

    public Animal(String name, String imageName, String soundName) {
        this.name = name;
        this.imageName = imageName;
        this.soundName = soundName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }
}
