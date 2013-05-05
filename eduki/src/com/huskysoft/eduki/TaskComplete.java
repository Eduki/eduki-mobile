
package com.huskysoft.eduki;

/**
 * @author Cody Thomas Interface TaskComplete represents a callback that takes a
 *         string as its only param
 */
public interface TaskComplete {

    /**
     * A callback method to pass a string to a class that implements
     * TaskComplete.
     * 
     * @param data A string provided through the callback, a result
     */
    public void taskComplete(String data);
}
