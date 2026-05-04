public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(0.393701 / 12.0);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    // Convert to base unit (FEET)
    public double toBase(double value) {
        return value * toFeetFactor;
    }

    // Convert from base unit (FEET)
    public double fromBase(double baseValue) {
        return baseValue / toFeetFactor;
    }
}