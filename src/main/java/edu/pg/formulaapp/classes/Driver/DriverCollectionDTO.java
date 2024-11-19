package edu.pg.formulaapp.classes.Driver;

/**
 * DTO for Driver entity
 */
public class DriverCollectionDTO {
    public String id;
    public String name;
    public String surname;

    /**
     * Default constructor of DriverCollectionDTO
     */
    public DriverCollectionDTO() {}

    /**
     * Constructor of DriverCollectionDTO
     * @param id id of the driver
     * @param name name of the driver
     * @param surname surname of the driver
     */
    public DriverCollectionDTO(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Function getId() returns id of the driver
     * @return id of the driver
     */
    public String getId() {
        return id;
    }

    /**
     * Function getName() returns name of the driver
     * @return name of the driver
     */
    public String getName() {
        return name;
    }

    /**
     * Function getSurname() returns surname of the driver
     * @return surname of the driver
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Function setId() sets id of the driver
     * @param id id of the driver
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Function setName() sets name of the driver
     * @param name name of the driver
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Function setSurname() sets surname of the driver
     * @param surname surname of the driver
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Function toString() returns string representation of the driver
     * @return string representation of the driver
     */
    @Override
    public String toString() {
        return "DriverCollectionDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
