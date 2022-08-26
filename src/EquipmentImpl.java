import java.util.Objects;

/**
 * This class implements the Equipment interface with all its mandated methods
 */
public class EquipmentImpl implements Equipment {

  private final String name;
  private final Integer serviceUrgency;
  // Consider: set service urgency

  /**
   * Constructs equipment with the given name and service urgency
   * @param name name of the equipment
   * @param serviceUrgency number of days until next service is due
   */
  public EquipmentImpl(String name, Integer serviceUrgency) {
    this.name = name;
    this.serviceUrgency = serviceUrgency;
  }

  /**
   * Returns the name of this equipment
   *
   * @return String the name of this equipment
   */
  @Override
  public String getEquipmentName() {
    return this.name;
  }

  /**
   * Returns the number of days until the next service is due. This can be
   * a positive integer, negative integer or 0. A negative integer means
   * service is past the due date
   *
   * @return Integer the number of days until the next service is due
   */
  @Override
  public Integer getServiceUrgency() {
    return this.serviceUrgency;
  }

  /**
   * Compares this equipment to the other equipment by number of days until the next service is due
   *
   * @param e the other equipment to be compared to this equipment
   * @return a negative integer, zero, or a positive integer if this equipment's next service is
   * sooner (less), same (zero), or later (greater) than the other equipment
   */
  @Override
  public Integer compareByServiceDue(Equipment e) {
    Objects.requireNonNull(e);
    return this.getServiceUrgency().compareTo(e.getServiceUrgency());
  }

  /**
   * Returns a string representing this equipment's name
   * @return String representing this equipment's name
   */
  public String toString() {
    return this.getEquipmentName();
  }
}
