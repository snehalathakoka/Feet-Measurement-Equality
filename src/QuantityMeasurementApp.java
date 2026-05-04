public class QuantityMeasurementApp {

    // Inner class Feet
    static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Override equals method
        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) return true;

            // Null or different class
            if (obj == null || getClass() != obj.getClass()) return false;

            // Type casting
            Feet other = (Feet) obj;

            // Compare double values safely
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Main method
    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        boolean result = f1.equals(f2);

        System.out.println("Are equal? " + result);
    }
}