import http from 'k6/http'
import { check,Trend  } from 'k6'
import { BASE_URL } from '../../utils/constants.js';

export function getPet(petId){
    
    let url = `${BASE_URL}/pet/${petId}`;
    let headers = { 'Content-Type': 'application/json' };
    let res = http.get(url, { 
        tags: { 
            endpoint: 'get_pet',
        },
    });

    check(res, {
        'status is 200': (r) => r.status === 200
    }, { endpoint: "get_pet" });

    return res;
}