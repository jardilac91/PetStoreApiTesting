import http from 'k6/http'
import { check } from 'k6'
import { BASE_URL } from '../../utils/constants.js';

export function logoutUser(){
    
    let url = `${BASE_URL}/user/logout`;
    let headers = { 'Content-Type': 'application/json' };
    let res = http.get(url, { 
        headers, 
        tags: { 
            endpoint: 'logout_user',
        },
    });

    check(res, {
        'status is 200': (r) => r.status === 200
    }, { endpoint: "logout_user" });

    return res;
}