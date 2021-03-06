// **********************************************************************
//
// Copyright (c) 2003-2018 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.7.1
//
// <auto-generated>
//
// Generated from file `user.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.cyzs.springbooticeentire.icegen.person;

public class Person implements Cloneable,
                               java.io.Serializable
{
    public long id;

    public String name;

    public int age;

    public String email;

    public String address;

    public Person()
    {
        this.name = "";
        this.email = "";
        this.address = "";
    }

    public Person(long id, String name, int age, String email, String address)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public boolean equals(Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Person r = null;
        if(rhs instanceof Person)
        {
            r = (Person)rhs;
        }

        if(r != null)
        {
            if(this.id != r.id)
            {
                return false;
            }
            if(this.name != r.name)
            {
                if(this.name == null || r.name == null || !this.name.equals(r.name))
                {
                    return false;
                }
            }
            if(this.age != r.age)
            {
                return false;
            }
            if(this.email != r.email)
            {
                if(this.email == null || r.email == null || !this.email.equals(r.email))
                {
                    return false;
                }
            }
            if(this.address != r.address)
            {
                if(this.address == null || r.address == null || !this.address.equals(r.address))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::demo::Person");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, id);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, name);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, age);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, email);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, address);
        return h_;
    }

    public Person clone()
    {
        Person c = null;
        try
        {
            c = (Person)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeLong(this.id);
        ostr.writeString(this.name);
        ostr.writeInt(this.age);
        ostr.writeString(this.email);
        ostr.writeString(this.address);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.id = istr.readLong();
        this.name = istr.readString();
        this.age = istr.readInt();
        this.email = istr.readString();
        this.address = istr.readString();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, Person v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public Person ice_read(com.zeroc.Ice.InputStream istr)
    {
        Person v = new Person();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Person> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, Person v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<Person> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(Person.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final Person _nullMarshalValue = new Person();

    public static final long serialVersionUID = -877543287L;
}
