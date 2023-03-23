public class RingBuffer {

    double[] buffer ;
    int first ;
    int last ;
    int capacity ;
    int size ;

    

    /**
     * Constructeur. prend en paramètre la capacité du buffer et
     * alloue le buffer.
     * @param cap
     */
    public RingBuffer(int cap){
        capacity = cap;
        first = 0 ;
        last = 0 ;
        size = 0 ;
        buffer = new double[cap] ;
    }
    

    public void display(){
        System.out.println("first : " + first);
        System.out.println("last :" + last);
        System.out.println("capacity :" + capacity);
        System.out.println("size :" + size);
        System.out.println("Buffer :");
        for (int i = 0; i < buffer.length; i++) {
            System.out.print(buffer[i] + " ");
        }
        System.out.println();
    }

    public boolean isEmpty(){
        boolean isEmpty = false;
        if (size == 0){
            isEmpty = true;
        }
        return isEmpty;
    }

    public boolean isFull(){
        boolean isFull = false;
        if (size == capacity){
            isFull = true;
        }
        return isFull;
    } 

    public int size(){
        return size ;
    }


    public double enqueue (double nombre){
        if( isFull() ) throw new RuntimeException("Erreur file pleine");

        
        buffer[last] = nombre;
        last = (last + 1)%capacity;
        size++;
        return nombre;
    }

    public double dequeue(){
        if( isEmpty() ) throw new RuntimeException("Erreur file est vide");

        double res = buffer[first];
        buffer[first] = 0;
        first = (first + 1)%capacity;
        size--;
        return res;
        
    }

    public double pick(){
        if( isEmpty() ) throw new RuntimeException("Erreur file est vide");
        return buffer[first];
    }


    /**
    * méthode de debug
    */
    public static void main(String[] args){
        RingBuffer buf = new RingBuffer(10);
        buf.enqueue(1.5);
        buf.enqueue(2.5);
        buf.enqueue(3.5);
        buf.enqueue(4.5);
        buf.enqueue(5.5);
        buf.enqueue(6.5);
        buf.enqueue(7.5);
        buf.enqueue(8.5);
        buf.enqueue(9.5);
        buf.enqueue(10);
        buf.dequeue();
        buf.dequeue();
        buf.dequeue();
        buf.dequeue();
        buf.display();
    }
}
