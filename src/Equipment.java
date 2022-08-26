/**
 * This interface represents a laboratory equipment
 */
public interface Equipment {

  /**
   * Returns the name of this equipment
   * @return String the name of this equipment
   */
  String getEquipmentName();

  /**
   * Returns the number of days until the next service is due. This can be
   * a positive integer, negative integer or 0. A negative integer means
   * service is past the due date
   * @return Integer the number of days until the next service is due
   */
  Integer getServiceUrgency();

  /**
   * Compares this equipment to the other equipment by number of days until
   * the next service is due
   * @param e the other equipment to be compared to this equipment
   * @return a negative integer, zero, or a positive integer if this equipment's
   * next service is sooner (less), same (zero), or later (greater) than the
   * other equipment
   */
  Integer compareByServiceDue(Equipment e);
}
