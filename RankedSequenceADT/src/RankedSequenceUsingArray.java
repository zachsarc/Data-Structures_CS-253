public class RankedSequenceUsingArray<T> {
    // write down implementation using array
    // returnItem(rank)
    // replaceItem(rank, newItem)
    // insertItem(rank,newItem)
    // deleteItem(rank)
    // size()
    // empty()
    // traverse()
        private Object[] a;
        private int size;

    public RankedSequenceUsingArray(int initialCapacity) {
            if (initialCapacity < 0) throw new IllegalArgumentException("capacity < 0");
            a = new Object[Math.max(1, initialCapacity)];
            size = 0;
        int rank = 0;
        }

        public int size() { return size; }
        public boolean isEmpty() { return size == 0; }

        // Helper for get/replace/delete
        private void checkRankGet(int rank) {
            if (rank < 0 || rank >= size)
                throw new IndexOutOfBoundsException("rank " + rank + " not in [0," + (size - 1) + "]");
        }

        // Helper for insert (can insert at end, so rank == size is allowed)
        private void checkRankInsert(int rank) {
            if (rank < 0 || rank > size)
                throw new IndexOutOfBoundsException("rank " + rank + " not in [0," + size + "]");
        }

        private void ensureCapacity(int minCap) {
            if (a.length >= minCap) return;
            int newCap = Math.max(a.length * 2, minCap);
            Object[] b = new Object[newCap];
            System.arraycopy(a, 0, b, 0, size);
            a = b;
        }

        // ==== Method 1: returnItem ====
        @SuppressWarnings("unchecked")
        public T returnItem(int rank) {
            if (rank < 0 || rank >= size) {
                throw new IndexOutOfBoundsException("Rank out of bounds");
            } else {
                return (T) a[rank];
            }
        }

        public void replaceItem(int rank, int newItem) {
        checkRankGet(rank);
        a[rank] = newItem;
        }

        public void insertItem(int rank, T newItem) {
        checkRankInsert(rank);
        if (size == a.length) {
            Object[] temp = new Object[size + 1];
            System.arraycopy(a, 0, temp, 0, size);
            a = temp;
        }
            for (int i = size - 1; i >= rank; i--) {
                a[i + 1] = a[i];
            }
            a[rank] = newItem;
            size++;
        }

        public void deleteItem(int rank) {

        }

        // Temporary helper
        public void pushBack(T item) {
            ensureCapacity(size + 1);
            a[size++] = item;
        }
}
