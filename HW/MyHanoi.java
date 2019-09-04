public class MyHanoi {
    public static void main(String[] args) {
        int maxDisk = 3;
        hanoiStack S = new hanoiStack(maxDisk);
        hanoiStack M = new hanoiStack(maxDisk);
        hanoiStack D = new hanoiStack(maxDisk);
        S.fillStack();
        IterativeHanoi(maxDisk,S,M,D);
    }
    public static void IterativeHanoi(int nDisk,hanoiStack start , hanoiStack mid,hanoiStack destination){
        char s = 'S';char m = 'M';char d = 'D';
        int maxMove = (int)(Math.pow(2,nDisk)-1);
        if(nDisk%2==0){
            char temp = m;
            m = d;
            d = temp;
        }
        for (int i = 1; i <= maxMove; i++) {
            if(i%3==1){
                moveBetweenPole(start,destination,s,d);
            }else if(i%3==2){
                moveBetweenPole(start,mid,s,m);
            }else if(i%3==0){
                moveBetweenPole(mid,destination,m,d);
            }
        }
    }
    public static void moveBetweenPole(hanoiStack start , hanoiStack destination , char source , char des){
        int itemFromStart = start.pop();
        int itemFromDes = destination.pop();
        if(itemFromStart == -1){
            start.push(itemFromDes);
            printMove(des,source,itemFromDes);
        }else if(itemFromDes == -1){
            destination.push(itemFromStart);
            printMove(source,des,itemFromStart);
        }else if(itemFromStart < itemFromDes){
            destination.push(itemFromDes);
            destination.push(itemFromStart);
            printMove(source,des,itemFromStart);
        }else if(itemFromStart > itemFromDes){
            start.push(itemFromStart);
            start.push(itemFromDes);
            printMove(des,source,itemFromDes);
        }
    }
    public static void printMove(char start , char end , int item){
        System.out.println("Move "+item+" from "+start+" to "+end);
    }
}

class hanoiStack{
    int[] stack;
    int totalSize;
    int currentSize=-1;

    public hanoiStack(int size) {
        stack = new int[size];
        totalSize = size;
    }
    public void push(int n){
        if (isFull())
            return;
        stack[++currentSize] = n;
    }
    public int pop(){
        if (isEmpty())
            return -1;
        return stack[currentSize--];
    }
    public boolean isEmpty(){
        return currentSize == -1;
    }
    public boolean isFull(){
        return totalSize-1 == currentSize;
    }
    public void fillStack(){
        for (int i = totalSize;i>=1;i--){
            this.push(i);
        }
    }
    public void printStack(){
        for (int i = currentSize; i >=0 ; i--) {
            System.out.println(stack[i]);
        }
    }
}
