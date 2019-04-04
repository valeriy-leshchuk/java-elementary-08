package org.demo.birds.entities;

/**
 * Bird.
 * Должны быть следущие свойства:
 * name  тип String
 * livingArea тип String
 * size – число с плавающей точкой, может быть незаполненным (Double).
 */
public class Bird
{
    private String name_;
    private String livingArea_;
    private Double size_;

    public Bird(String name, String livingArea, Double size)
    {
        name_ = name;
        livingArea_ = livingArea;
        size_ = size;
    }

    public String getName()
    {
        return name_;
    }

    public String getLivingArea()
    {
        return livingArea_;
    }

    @Override
    public String toString()
    {
        return "Bird: name=" + name_ + ", livingArea=" + livingArea_ + ", size=" + size_;
    }
}
