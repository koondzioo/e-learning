import Enzyme, {configure} from 'enzyme';
import EnzymeAdapter from 'enzyme-adapter-react-16';

// importowanie do naszego projektu Enzyme i ustawienie adaptera do uruchamiania testow
configure({adapter: new EnzymeAdapter()});