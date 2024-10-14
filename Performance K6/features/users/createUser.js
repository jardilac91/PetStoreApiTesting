import http from 'k6/http'
import {check} from 'k6'
import { BASE_URL } from '../../utils/constants.js';
import { requestCreateUser } from '../../utils/constants.js';


export function createUser(){
    let userData = requestCreateUser();
    let url = `${BASE_URL}/store/order`;
    let headers = { 'Content-Type': 'application/json' };
    let res = http.post(url, JSON.stringify(userData), {
        headers, 
        tags: { endpoint: 'create_user' }});

    check(res, {
        'status is 200': (r) => r.status === 200
    })

    return res;
}