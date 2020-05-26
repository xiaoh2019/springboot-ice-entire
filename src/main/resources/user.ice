module demo{
    struct User{
	    string name;
		int age;
		string email;
		string address;
	    int id;
	};

	interface UserService{
	    User getUserByName(string name);
	    void addUser(User user);
		void deleteUserById(int id);
		void updateUser(User user);
	};

}

