import { group } from 'k6';
import { ID, TEST_TYPE, USERNAME } from './utils/constants.js';
import { getPet } from './features/pets/getPet.js';
import { createPet } from './features/pets/createPet.js';
import { getOrder } from './features/orders/getOrder.js';
import { createOrder } from './features/orders/createOrder.js';
import { testConfig } from './config.js';
import { getUser } from './features/users/getUser.js';
import { loginUser } from './features/users/loginUser.js';
import { logoutUser } from './features/users/logoutUser.js';
import { createUser } from './features/users/createUser.js';


const config = testConfig[TEST_TYPE] || testConfig.load;

export let options = {
    stages: config.stages,
    thresholds: config.thresholds,
}
    
export default function(){
    group("pets", function(){
        getPet(ID);
        createPet();
    });
    group("Orders", function(){
        getOrder(ID);
        createOrder()
    });
    group("Users", function(){
        getUser(USERNAME);
        loginUser();
        logoutUser();
        createUser();
    });
}

