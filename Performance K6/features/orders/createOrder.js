import http from 'k6/http'
import {check} from 'k6'
import { BASE_URL } from '../../utils/constants.js';
import { requestCreateOrder } from '../../utils/constants.js';


export function createOrder(){
    let orderData = requestCreateOrder();
    let url = `${BASE_URL}/store/order`;
    let headers = { 'Content-Type': 'application/json' };
    let res = http.post(url, JSON.stringify(orderData), {
        headers, 
        tags: { endpoint: 'create_order' }});

    check(res, {
        'status is 200': (r) => r.status === 200
    })

    return res;
}