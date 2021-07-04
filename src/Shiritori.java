public class Shiritori {

    String[] words = {};
    boolean game_over;

    public Object play(String word){

        String[] temp = new String[words.length + 1];

        for(int i = 0; i < words.length;i++){
            if(words[i].equals(word)){
                game_over = true;
                return "game over";
            }
            temp[i] = words[i];
        }

        temp[words.length] = word;

        if(temp.length > 1){
            if (temp[temp.length -1].charAt(0) != temp[temp.length-2].charAt(temp[temp.length-2].length()-1)){
                game_over = true;
                return "game over";
            }

        }

        words = temp;

        return words;
    }

    public String[] getWords() {
        return words;
    }

    public String restart(){
        words = new String[]{};
        game_over = false;
        return "game restarted";
    }
}
