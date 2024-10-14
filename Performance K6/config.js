export const testConfig = {
    load: {
        stages: [
            { duration: '1m', target: 50 },
            { duration: '1m', target: 50 },
            { duration: '1m', target: 0}
        ],
        thresholds: {
            'http_req_duration': ['p(95)<1000', 'avg<800'],
            'checks': ['rate>0.99'],
        }
    },
    stress:{
        stages: [
            { duration: '1m', target: 100 },
            { duration: '2m', target: 200 },
            { duration: '3m', target: 300 }, 
            { duration: '2m', target: 400 },
            { duration: '1m', target: 50  },
        ],
        thresholds: {
            'http_req_duration': ['p(95)<1200', 'avg<1200'],
            'checks': ['rate>0.95'],
        }
    },
    spike:{
        stages: [
            { duration: '1m', target: 50  },
            { duration: '3m', target: 400 },
            { duration: '1m', target: 50  },  
        ],
        thresholds:{
            'http_req_duration': ['p(95)<1500', 'avg<1800'],
            'checks': ['rate>0.90'],
        }
    }
}


