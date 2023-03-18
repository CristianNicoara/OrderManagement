package businessLayer.validators;

/**
 * @Author: Nicoara Cristian-Catalin, student at Technical University of Cluj-Napoca, Romania
 *
 * @Since: Apr 16, 2022
 * @Source: https://gitlab.com/utcn_dsrl/pt-layered-architecture
 */
public interface Validator<T> {
    public void validate(T t);
}
