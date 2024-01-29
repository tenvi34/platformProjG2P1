import axios from 'axios';

class UserProxy {
    static async signUp(email, password, name) {
        console.log(`[EFFECT] signUp : <email:${email}, password:${password}, name:${name}>`)
        
        const reqDto = {
            "email": email,
            "password": password,
            "name": name
        }
        const response = await axios.put(`http://${window.location.host}/api/user/users/signUp`, reqDto);
        
        console.log(response)
    }

    static async signIn(email, password) {
        console.log(`[EFFECT] signIn : <email:${email}, password:${password}>`)
        
        const reqDto = {
            "email": email,
            "password": password
        }
        const jwtToken = (await axios.put(`http://${window.location.host}/api/user/users/signIn`, reqDto)).headers.authorization;
    
        console.log("jwtToken :" + jwtToken);
        return jwtToken;
    }
}

export default UserProxy