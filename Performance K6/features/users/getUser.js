import http from 'k6/http'
import { check } from 'k6'
import { BASE_URL } from '../../utils/constants.js';

export function getUser(userName){
    
    let url = `${BASE_URL}/user/${userName}`;
    let headers = { 'Content-Type': 'application/json' };
    let res = http.get(url, { 
        headers, 
        tags: { 
            endpoint: 'get_user',
        },
    });

    check(res, {
        'status is 200': (r) => r.status === 200
    }, { endpoint: "get_user" });

    return res;
}