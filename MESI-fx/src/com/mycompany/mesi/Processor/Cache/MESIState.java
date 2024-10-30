package com.mycompany.mesi.Processor.Cache;

/**
 * Enum representing the possible states in the MESI (Modified, Exclusive, Shared, Invalid) 
 * protocol for cache coherence in multiprocessor systems.
 * Each state describes the status of a cache line in relation to other caches and main memory.
 */
public enum MESIState {
    /**
     * The MODIFIED state indicates that the cache line has been modified.
     * It is different from the copy in main memory and is the only valid copy 
     * in the entire system. Other caches do not hold this line in any state.
     */
    MODIFIED,

    /**
     * The EXCLUSIVE state indicates that the cache line matches the main memory,
     * and no other caches contain this line. It is the only copy in the cache system.
     */
    EXCLUSIVE,

    /**
     * The SHARED state indicates that the cache line can be present in multiple caches.
     * This line is consistent with the main memory, but other caches may also hold a copy.
     */
    SHARED,

    /**
     * The INVALID state indicates that the cache line is invalid and cannot be used.
     * The line must be fetched again from the main memory or another cache if needed.
     */
    INVALID
}
