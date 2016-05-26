package com.softacad.snippets.stack;

import org.junit.Assert;
import org.junit.Test;

public class ArrayStackTest2 {

    @Test
    public void testStackShouldPopTheLastPushedItem() {
        Stack<String> stack = new ArrayStack<>(5);
        stack.push("Something");
        stack.push("Something2");
        stack.push("Something3");

        String item = stack.pop();

        Assert.assertTrue(item.equals("Something3"));
    }

    @Test
    public void testWhenItemIsPoppedSizeShouldBeDecreasedByOne() {
        Stack<String> stack = new ArrayStack<>(5);
        stack.push("Something");
        stack.push("Something2");

        int currentSize = stack.getSize();
        stack.pop();

        Assert.assertTrue(stack.getSize() == (currentSize - 1));
    }

    @Test
    public void testWhenStackIsCreatedStackShouldBeEmpty() {
        Stack<String> stack = new ArrayStack<>(5);

        Assert.assertTrue(stack.isEmpty());
    }

    @Test
    public void testPushShouldIncrementStackSizeByOne() {
        Stack<String> stack = new ArrayStack<>(5);
        stack.push("Something");

        Assert.assertTrue(stack.getSize() == 1);
    }
}
