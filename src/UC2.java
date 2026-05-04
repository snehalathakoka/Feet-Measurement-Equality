public class UC2 {

    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    static class Inch {
        private final double value;

        public Inch(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Inch other = (Inch) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    public static void main(String[] args) {

        // Feet test
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        System.out.println("UC2 - Feet Equality: " + f1.equals(f2));

        // Inch test
        Inch i1 = new Inch(1.0);
        Inch i2 = new Inch(1.0);
        Inch i3 = new Inch(2.0);

        System.out.println("UC2 - Inch (same): " + i1.equals(i2));
        System.out.println("UC2 - Inch (different): " + i1.equals(i3));
    }
}