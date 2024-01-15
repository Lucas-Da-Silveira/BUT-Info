import java.io.File;
import java.util.*;

public class Joueur {

    public Joueur(String nom,Queue<Carte> main) {
        this.nom = nom;
        this.main = main;
    }

    public String nom;
    public Queue<Carte> main = new Queue<Carte>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Carte> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Carte carte) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Carte> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean offer(Carte carte) {
            return false;
        }

        @Override
        public Carte remove() {
            return null;
        }

        @Override
        public Carte poll() {
            return null;
        }

        @Override
        public Carte element() {
            return null;
        }

        @Override
        public Carte peek() {
            return null;
        }
    };
}
