package model;

/**
 * Outsourced parts model class
 *
 * @author William Gunn
 */
public class Outsourced extends Part {

    /**
     * The vendor company name
     */
    private String companyName;

    /**
     * Constructor method for an in house part
     *
     * @param id           Part ID
     * @param name         Part name
     * @param price        Part price
     * @param stock        Part stock
     * @param min          Minimum part Stock
     * @param max          Maximum part Stock
     * @param companyName  Vendor company name
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Company name getter
     *
     * @return companyName
     */
    public int getCompanyName() {
        return companyName;
    }

    /**
     * Company name setter
     *
     * @param machineId
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
