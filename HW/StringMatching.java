public class StringMatching {
    public static void main(String[] args) {
        int n = 5;
        char[][] wordPuzzle = new char[][]{{'A', 'B', 'C', 'D', 'E',},
                                             {'F', 'G', 'H', 'I', 'J',},
                                             {'K', 'L', 'M', 'N', 'O',},
                                             {'P', 'Q', 'R', 'S', 'T',},
                                             {'U', 'V', 'W', 'X', 'Y'}};
        String[] pattern = {"ABCDE","EDCBA","AFKPU","UPKFA","AGMSY","YSMGA","UQMIE","EIMQU"};
        answerPuzzle[] arrayOfAns = new answerPuzzle[pattern.length];
        for (int i = 0; i < pattern.length; i++) {
            answerPuzzle ans = new answerPuzzle();
            String curPat = pattern[i];
            positionOfAnswer[] curPos = ans.findAnswer(wordPuzzle,curPat);
            if (curPos ==null){
                System.out.println("There is no answer for " + curPat);
            }else{
                System.out.print("Position of " + curPat+" is ");
                for (int j = 0; j < curPos.length; j++) {
                    System.out.print(curPos[j]);
                }
                System.out.println();
            }

        }

    }

}
class answerPuzzle{
    positionOfAnswer[] array ;
    String pattern;
    public answerPuzzle() {
    }

    public positionOfAnswer[] findAnswer(char[][] puzzle , String pattern){
        this.pattern = pattern;
        array = new positionOfAnswer[pattern.length()];
        int index = 0;
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (puzzle[i][j] == pattern.charAt(index)){
                    int indexI = i;
                    int indexJ = j;
                    //horizontal right
                    while (index<pattern.length()&&isInArray(puzzle,indexI,indexJ)&&
                            puzzle[indexI][indexJ] == pattern.charAt(index)){
                        positionOfAnswer answer = new positionOfAnswer(indexI,indexJ);
                        array[index] = answer;
                        index++;
                        indexJ++;

                    }
                    if(index == pattern.length()){
                        return array;
                    }else {
                        index=0;
                        indexI = i;
                        indexJ = j;
                    }
                    //horizontal left
                    while (index<pattern.length()&&isInArray(puzzle,indexI,indexJ)&&
                            puzzle[indexI][indexJ] == pattern.charAt(index)){
                        positionOfAnswer answer = new positionOfAnswer(indexI,indexJ);
                        array[index] = answer;
                        index++;
                        indexJ--;

                    }
                    if(index == pattern.length()){
                        return array;
                    }else {
                        index=0;
                        indexI = i;
                        indexJ = j;
                    }
                    //vertical up
                    while (index<pattern.length()&&isInArray(puzzle,indexI,indexJ)&&
                            puzzle[indexI][indexJ] == pattern.charAt(index)){
                        positionOfAnswer answer = new positionOfAnswer(indexI,indexJ);
                        array[index] = answer;
                        index++;
                        indexI--;

                    }
                    if(index == pattern.length()){
                        return array;
                    }else {
                        index=0;
                        indexI = i;
                        indexJ = j;
                    }
                    //Vertical down
                    while (index<pattern.length()&&isInArray(puzzle,indexI,indexJ)&&
                            puzzle[indexI][indexJ] == pattern.charAt(index)){
                        positionOfAnswer answer = new positionOfAnswer(indexI,indexJ);
                        array[index] = answer;
                        index++;
                        indexI++;

                    }
                    if(index == pattern.length()){
                        return array;
                    }else {
                        index=0;
                        indexI = i;
                        indexJ = j;
                    }
                    //Top right
                    while (index<pattern.length()&&isInArray(puzzle,indexI,indexJ)&&
                            puzzle[indexI][indexJ] == pattern.charAt(index)){
                        positionOfAnswer answer = new positionOfAnswer(indexI,indexJ);
                        array[index] = answer;
                        index++;
                        indexJ++;
                        indexI--;
                    }
                    if(index == pattern.length()){
                        return array;
                    }else {
                        index=0;
                        indexI = i;
                        indexJ = j;
                    }
                    //Down right
                    while (index<pattern.length()&&isInArray(puzzle,indexI,indexJ)&&
                            puzzle[indexI][indexJ] == pattern.charAt(index)){
                        positionOfAnswer answer = new positionOfAnswer(indexI,indexJ);
                        array[index] = answer;
                        index++;
                        indexJ++;
                        indexI++;
                    }
                    if(index == pattern.length()){
                        return array;
                    }else {
                        index=0;
                        indexI = i;
                        indexJ = j;
                    }
                    //top left
                    while (index<pattern.length()&&isInArray(puzzle,indexI,indexJ)&&
                            puzzle[indexI][indexJ] == pattern.charAt(index)){
                        positionOfAnswer answer = new positionOfAnswer(indexI,indexJ);
                        array[index] = answer;
                        index++;
                        indexJ--;
                        indexI--;
                    }
                    if(index == pattern.length()){
                        return array;
                    }else {
                        index=0;
                        indexI = i;
                        indexJ = j;
                    }
                    //down left
                    while (index<pattern.length()&&isInArray(puzzle,indexI,indexJ)&&
                            puzzle[indexI][indexJ] == pattern.charAt(index)){
                        positionOfAnswer answer = new positionOfAnswer(indexI,indexJ);
                        array[index] = answer;
                        index++;
                        indexJ--;
                        indexI++;
                    }
                    if(index == pattern.length()){
                        return array;
                    }else {
                        index=0;
                        indexI = i;
                        indexJ = j;
                    }
                }
            }
        }
        return null;
    }

    public boolean isInArray(char[][] puzzle,int i , int j){
        return i>=0&&i<puzzle.length&&j>=0&&j<puzzle[i].length;
    }
}
class positionOfAnswer{
    int x;
    int y;
    public positionOfAnswer(int x , int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "("+x+","+y+")";
    }
}
