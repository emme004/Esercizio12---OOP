package p12.exercise;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MultiQueueImpl<T, Q> implements MultiQueue<T, Q>{
    private Map<Q, Queue<T>> map;

    

    public MultiQueueImpl(Map<Q, Queue<T>> map) {
        map = new HashMap<>();
    }

    @Override
    public Set<Q> availableQueues() {
        return map.keySet();    
    }

    @Override
    public void openNewQueue(Q queue) throws IllegalArgumentException {
        if(map.containsKey(queue)){
            throw new IllegalArgumentException("The queue is already available");
        }
        map.put(queue, new LinkedList<T>());
    
    }

    @Override
    public boolean isQueueEmpty(Q queue) throws IllegalArgumentException{
        if(!map.containsKey(queue)){
            throw new IllegalArgumentException("The queue is not available");
        }    
        return map.get(queue).isEmpty();
    }

    @Override
    public void enqueue(T elem, Q queue) throws IllegalArgumentException{
        if(!map.containsKey(queue)){
            throw new IllegalArgumentException("The queue is not available");
        } 
        map.get(queue).add(elem);
    }

    @Override
    public T dequeue(Q queue) throws IllegalArgumentException {
        if(!map.containsKey(queue)){
            throw new IllegalArgumentException("The queue is not available");
        } 
        return map.get(queue).poll();
    }

    @Override
    public Map<Q, T> dequeueOneFromAllQueues() {
        Map<Q, T> dequeuedElement = new HashMap<>();
        for(Q queue : map.keySet()){
            dequeuedElement.put(queue, map.get(queue).poll());
        }
        return dequeuedElement;
    }

    @Override
    public Set<T> allEnqueuedElements() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'allEnqueuedElements'");
    }

    @Override
    public List<T> dequeueAllFromQueue(Q queue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dequeueAllFromQueue'");
    }

    @Override
    public void closeQueueAndReallocate(Q queue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'closeQueueAndReallocate'");
    }

}
