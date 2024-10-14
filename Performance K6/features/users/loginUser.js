import http from 'k6/http'
import { check } from 'k6'
import { BASE_URL } from '../../utils/constants.js';

export function loginUser(){
    
    let url = `${BASE_URL}/user/login?username=user2&password=123`;
    let headers = { 'Content-Type': 'application/json' };
    let res = http.get(url, { 
        headers, 
        tags: { 
            endpoint: 'login_user',
        },
    });

    check(res, {
        'status is 200': (r) => r.status === 200
    }, { endpoint: "login_user" });

    return res;
}