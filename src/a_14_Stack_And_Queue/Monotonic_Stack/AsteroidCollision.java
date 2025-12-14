package a_14_Stack_And_Queue.Monotonic_Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AsteroidCollision {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(asteroidCollision(new int[]{1, 2, 3, -4, -2})));
        System.out.println(Arrays.toString(asteroidCollision(new int[]{5, 10, -5, -10, 8, -8, -3, 12})));
        System.out.println(Arrays.toString(asteroidCollision(new int[]{10, 2, -5})));
    }

    public static int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        List<Integer> st = new ArrayList<>();

        // Traverse all the asteroids
        for(int i = 0; i < n; i++) {

            /* Push the asteroid in stack if a
            right moving asteroid is seen */
            if(asteroids[i] > 0) {
                st.add(asteroids[i]);
            }

            /* Else if the asteroid is moving
            left, perform the collisions */
            else {

                /* Until the right moving asteroids are
                smaller in size, keep on destroying them */
                while(!st.isEmpty() && st.get(st.size() - 1) > 0 &&
                        st.get(st.size() - 1) < Math.abs(asteroids[i])) {
                    // Destroy the asteroid
                    st.remove(st.size() - 1);
                }

                /* If there is right moving asteroid
                which is of same size */
                if(!st.isEmpty() && st.get(st.size() - 1) == Math.abs(asteroids[i])) {
                    // Destroy both the asteroids
                    st.remove(st.size() - 1);
                }

                /* Otherwise, if there is no left
                moving asteroid, the right moving
                asteroid will not be destroyed */
                else if(st.isEmpty() || st.get(st.size() - 1) < 0){

                    // Storing the array in final state
                    st.add(asteroids[i]);
                }
            }
        }
        int[] result = new int[st.size()];
        for(int i = 0; i < st.size(); i++) {
            result[i] = st.get(i);
        }
        return result;
    }
}
