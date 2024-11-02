package p12.exercise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MultiQueueImpl<T, Q> implements MultiQueue<T, Q>{
    private Map<Q, Queue<T>> map;  

    public MultiQueueImpl() {
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
        Set<T> enqueuedElements = new HashSet<>();
        for(Q queue : map.keySet()){
            enqueuedElements.addAll(map.get(queue));
        }
        return enqueuedElements;
    }

    @Override
    public List<T> dequeueAllFromQueue(Q queue) throws IllegalArgumentException{
       List<T> dequeueAll = new LinkedList<>();
       if(!map.containsKey(queue)){
            throw new IllegalArgumentException();
        }
       dequeueAll.addAll(map.get(queue));
       map.get(queue).clear();
       return dequeueAll;
    }

    @Override
    public void closeQueueAndReallocate(Q queue) {
        if(!map.containsKey(queue)){
            throw new IllegalArgumentException();
        }

        if(map.keySet().size() == 1){
            throw new IllegalStateException();
        }

        for(Q queues : map.keySet()){
            if(!queues.equals(queue)){
                map.get(queues).addAll(dequeueAllFromQueue(queue));
                break;
            }
        }
        map.remove(queue);
    }
}
