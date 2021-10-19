package model;

/**
 * In-House parts model class
 *
 * @author William Gunn
 */
public class InHouse extends Part {

    /**
     * The machine ID
     */
    private int machineId;

    /**
     * Constructor method for an in house part
     *
     * @param id          Part ID
     * @param name        Part name
     * @param price       Part price
     * @param stock       Part stock
     * @param min         Minimum part Stock
     * @param max         Maximum part Stock
     * @param machineId   Machine ID
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Machine ID getter
     *
     * @return machineId
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Machine ID setter
     *
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
