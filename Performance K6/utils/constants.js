export const BASE_URL = 'http://127.0.0.1:8080/api/v3';

export const TEST_TYPE = __ENV.TEST_TYPE || 'load'; 

export const ID = 1;

export const USERNAME = "user1";

export function generateRandomId(){
    return Math.floor(Math.random()*10000)+100;
}

export function requestCreatePet() {
    return {
        id: generateRandomId(),
        name: "doggie",
        category: {
            id: 1,
            name: "Dogs"
        },
        photoUrls: ["string"],
        tags: [
            {
                id: 0,
                name: "string"
            }
        ],
        status: "available"
    }
}

export function requestCreateOrder() {
    return {
        id: generateRandomId(),
        petId: generateRandomId(),
        quantity: generateRandomId(),
        shipDate:  "2024-10-13T18:22:25.347Z",
        status: "aprovved",
        complete: true
    }
}

export function requestCreateUser() {
    return {
        id: generateRandomId(),
        userName: "k6user",
        firstName: "k6FirstName",
        lastName:  "k6LastName",
        email: "k6@test.com",
        password: "abc123",
        phone: "123456789",
        userStatus: generateRandomId() 
    }
}