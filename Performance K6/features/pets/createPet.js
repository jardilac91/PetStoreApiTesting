import http from 'k6/http'
import {check} from 'k6'
import { BASE_URL } from '../../utils/constants.js';
import { requestCreatePet } from '../../utils/constants.js';


export function createPet(){
    let petData = requestCreatePet();
    let url = `${BASE_URL}/pet`;
    let headers = { 'Content-Type': 'application/json' };
    let res = http.post(url, JSON.stringify(petData), {
        headers, 
        tags: { endpoint: 'create_pet' }});

    check(res, {
        'status is 200': (r) => r.status === 200
    })

    return res;
}