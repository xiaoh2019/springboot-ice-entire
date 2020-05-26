module demo{
    struct Person{
        long id;
        string name;
        int age;
        string email;
        string address;
    };

    interface PersonService{
        Person getPersonByName(string name);
        void addPerson(Person person);
        void update(Person p);
        void deletePersonById(long id);
    };
};