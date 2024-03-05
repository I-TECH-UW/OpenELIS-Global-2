class TestProperties {

    DEFAULT_USERNAME = "admin";
    DEFAULT_PASSWORD = "adminADMIN!";
    // static TEST_SERVER_URL = "https://openelis28.openelis-global.org/";
    static TEST_SERVER_URL = "https://localhost/";

    constructor() {
    }

    getUsername() {
        return this.DEFAULT_USERNAME
    }

    getPassword() {
        return this.DEFAULT_PASSWORD
    }
}

export default TestProperties;
