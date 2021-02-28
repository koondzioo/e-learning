// TA FUNKCJA POZWALA WYCIAGAC WSZYSTKIE KOMPONENTY DOPASOWANE DO ATRYBUTU
import {checkPropTypes} from "prop-types";

/**
 *
 * @param wrapper
 * @param attributeValue
 * @returns {*}
 */
export const findByTestAttr = (wrapper, attributeValue) =>
    wrapper.find(`[data-test='${attributeValue}']`);

export const checkProps = (component, confirmingProps) => {
    // mozesz zastosowac specjalna funkcje ktora pozwoli sprawdzic
    // czy masz prop
    // https://www.npmjs.com/package/check-prop-types
    const propError = checkPropTypes(
        component.propTypes,
        confirmingProps,
        'prop',
        component.name
    );
    expect(propError).toBeUndefined(); // jezeli nie ma zadnych bledow zwiazanych z props to
    // propError bedzie undefined
}