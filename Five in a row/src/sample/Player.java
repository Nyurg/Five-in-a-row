package sample;

public class Player implements java.io.Serializable {
    //attributes
    private int playerNumber;
    private String image;

    //constructors
    public Player() { }
    public Player(int playerNumber, String image) {
        this.playerNumber = playerNumber;
        this.image = image;
    }

    //getters
    public int getPlayerNumber() { return playerNumber; }
    public String getImage() { return image; }
}
