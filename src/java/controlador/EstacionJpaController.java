/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import entity.Estacion;
import entity.EstacionPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Linea;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Kelvins Insua
 */
public class EstacionJpaController implements Serializable {

    public EstacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estacion estacion) throws PreexistingEntityException, Exception {
        if (estacion.getEstacionPK() == null) {
            estacion.setEstacionPK(new EstacionPK());
        }
        estacion.getEstacionPK().setIdLinea(estacion.getLinea().getIdLinea());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Linea linea = estacion.getLinea();
            if (linea != null) {
                linea = em.getReference(linea.getClass(), linea.getIdLinea());
                estacion.setLinea(linea);
            }
            em.persist(estacion);
            if (linea != null) {
                linea.getEstacionList().add(estacion);
                linea = em.merge(linea);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstacion(estacion.getEstacionPK()) != null) {
                throw new PreexistingEntityException("Estacion " + estacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estacion estacion) throws NonexistentEntityException, Exception {
        estacion.getEstacionPK().setIdLinea(estacion.getLinea().getIdLinea());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
//            Estacion persistentEstacion = em.find(Estacion.class, estacion.getEstacionPK());
//            Linea lineaOld = persistentEstacion.getLinea();
//            Linea lineaNew = estacion.getLinea();
//            if (lineaNew != null) {
//                lineaNew = em.getReference(lineaNew.getClass(), lineaNew.getIdLinea());
//                estacion.setLinea(lineaNew);
//            }
            estacion = em.merge(estacion);
//            if (lineaOld != null && !lineaOld.equals(lineaNew)) {
//                lineaOld.getEstacionList().remove(estacion);
//                lineaOld = em.merge(lineaOld);
//            }
//            if (lineaNew != null && !lineaNew.equals(lineaOld)) {
//                lineaNew.getEstacionList().add(estacion);
//                lineaNew = em.merge(lineaNew);
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EstacionPK id = estacion.getEstacionPK();
                if (findEstacion(id) == null) {
                    throw new NonexistentEntityException("The estacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EstacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estacion estacion;
            try {
                estacion = em.getReference(Estacion.class, id);
                estacion.getEstacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estacion with id " + id + " no longer exists.", enfe);
            }
//            Linea linea = estacion.getLinea();
//            if (linea != null) {
//                linea.getEstacionList().remove(estacion);
//                linea = em.merge(linea);
//            }
            em.remove(estacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estacion> findEstacionEntities() {
        return findEstacionEntities(true, -1, -1);
    }

    public List<Estacion> findEstacionEntities(int maxResults, int firstResult) {
        return findEstacionEntities(false, maxResults, firstResult);
    }

    private List<Estacion> findEstacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estacion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Estacion findEstacion(EstacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estacion> rt = cq.from(Estacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Estacion> ordenarAscendente(int idLinea) {
        EntityManager em=getEntityManager();
        try {
            
            TypedQuery<Estacion> cq=em.createNamedQuery("Estacion.findByIdLineaAscendente",Estacion.class);
            cq.setParameter("idLinea", idLinea);
             return cq.getResultList();
           } finally {
            em.close();
        }  
      
        
    }

    public List<Estacion> ordenarDescendente(int idLinea) {
         EntityManager em=getEntityManager();
        try {
            
            TypedQuery<Estacion> cq=em.createNamedQuery("Estacion.findByIdLineaDescendente",Estacion.class);
            cq.setParameter("idLinea", idLinea);
             return cq.getResultList();
           } finally {
            em.close();
        }  
      
    }
    
    public List<Estacion> buscarEstacion(int idLinea) {
       EntityManager em=getEntityManager();
        try {
            
            TypedQuery<Estacion> cq=em.createNamedQuery("Estacion.findByIdLinea",Estacion.class);
            cq.setParameter("idLinea", idLinea);
             return cq.getResultList();
           } finally {
            em.close();
        }   
    }
     public Estacion buscarEstacionPK(int idLinea, String idNombreEstacion) {
       EntityManager em=getEntityManager();
        try {
            
            TypedQuery<Estacion> cq=em.createNamedQuery("Estacion.findByEstacionPK",Estacion.class);
            cq.setParameter("idLinea", idLinea);
            cq.setParameter("idNombreEstacion", idNombreEstacion);
             return cq.getSingleResult();
           } finally {
            em.close();
        }   
    }

}
