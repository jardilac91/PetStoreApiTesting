import http from 'k6/http'
import { check,Trend  } from 'k6'
import { BASE_URL } from '../../utils/constants.js';

export function getOrder(orderId){
    
    let url = `${BASE_URL}/store/order/${orderId}`;
    let headers = { 'Content-Type': 'application/json' };
    let res = http.get(url, headers, { 
        tags: { 
            endpoint: 'get_order',
        },
    });

    check(res, {
        'status is 200': (r) => r.status === 200
    }, { endpoint: "get_order" });

    return res;
}