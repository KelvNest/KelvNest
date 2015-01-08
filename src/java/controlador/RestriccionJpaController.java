/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Linea;
import entity.Restriccion;
import entity.RestriccionPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Kelvins Insua
 */
public class RestriccionJpaController implements Serializable {

    public RestriccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Restriccion restriccion) throws PreexistingEntityException, Exception {
        if (restriccion.getRestriccionPK() == null) {
            restriccion.setRestriccionPK(new RestriccionPK());
        }
        restriccion.getRestriccionPK().setIdLinea(restriccion.getLinea().getIdLinea());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Linea linea = restriccion.getLinea();
            if (linea != null) {
                linea = em.getReference(linea.getClass(), linea.getIdLinea());
                restriccion.setLinea(linea);
            }
            em.persist(restriccion);
            if (linea != null) {
                linea.getRestriccionList().add(restriccion);
                linea = em.merge(linea);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRestriccion(restriccion.getRestriccionPK()) != null) {
                throw new PreexistingEntityException("Restriccion " + restriccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Restriccion restriccion) throws NonexistentEntityException, Exception {
        restriccion.getRestriccionPK().setIdLinea(restriccion.getLinea().getIdLinea());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
//            Restriccion persistentRestriccion = em.find(Restriccion.class, restriccion.getRestriccionPK());
//            Linea lineaOld = persistentRestriccion.getLinea();
//            Linea lineaNew = restriccion.getLinea();
//            if (lineaNew != null) {
//                lineaNew = em.getReference(lineaNew.getClass(), lineaNew.getIdLinea());
//                restriccion.setLinea(lineaNew);
//            }
            restriccion = em.merge(restriccion);
//            if (lineaOld != null && !lineaOld.equals(lineaNew)) {
//                lineaOld.getRestriccionList().remove(restriccion);
//                lineaOld = em.merge(lineaOld);
//            }
//            if (lineaNew != null && !lineaNew.equals(lineaOld)) {
//                lineaNew.getRestriccionList().add(restriccion);
//                lineaNew = em.merge(lineaNew);
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RestriccionPK id = restriccion.getRestriccionPK();
                if (findRestriccion(id) == null) {
                    throw new NonexistentEntityException("The restriccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RestriccionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Restriccion restriccion;
            try {
                restriccion = em.getReference(Restriccion.class, id);
                restriccion.getRestriccionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The restriccion with id " + id + " no longer exists.", enfe);
            }
//            Linea linea = restriccion.getLinea();
//            if (linea != null) {
//                linea.getRestriccionList().remove(restriccion);
//                linea = em.merge(linea);
//            }
            em.remove(restriccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Restriccion> findRestriccionEntities() {
        return findRestriccionEntities(true, -1, -1);
    }

    public List<Restriccion> findRestriccionEntities(int maxResults, int firstResult) {
        return findRestriccionEntities(false, maxResults, firstResult);
    }

    private List<Restriccion> findRestriccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Restriccion.class));
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

    public Restriccion findRestriccion(RestriccionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Restriccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getRestriccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Restriccion> rt = cq.from(Restriccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<Restriccion> buscarIdLineaAscendente(int idLinea) {
      EntityManager em=getEntityManager();
        try {
            
            TypedQuery<Restriccion> cq=em.createNamedQuery("Restriccion.findByIdLineaAscendente",Restriccion.class);
            cq.setParameter("idLinea", idLinea);
             return cq.getResultList();
           } finally {
            em.close();
        }  
    }

    public List<Restriccion> buscarIdLineaDescendente(int idLinea) {
       EntityManager em=getEntityManager();
        try {
            
            TypedQuery<Restriccion> cq=em.createNamedQuery("Restriccion.findByIdLineaDescendente",Restriccion.class);
            cq.setParameter("idLinea", idLinea);
             return cq.getResultList();
           } finally {
            em.close();
        }   
    }
 public List<Restriccion> buscarRestriccion(int idLinea) {
       EntityManager em=getEntityManager();
        try {
            
            TypedQuery<Restriccion> cq=em.createNamedQuery("Restriccion.findByIdLinea",Restriccion.class);
            cq.setParameter("idLinea", idLinea);
             return cq.getResultList();
           } finally {
            em.close();
        }   
    }
  public Restriccion buscarRestriccionPK(int idLinea, int idRestriccion) {
       EntityManager em=getEntityManager();
        try {
            
            TypedQuery<Restriccion> cq=em.createNamedQuery("Restriccion.findByRestriccionPK",Restriccion.class);
            cq.setParameter("idLinea", idLinea);
            cq.setParameter("idRestriccion", idRestriccion);
             return cq.getSingleResult();
           } finally {
            em.close();
        }   
    }

    
}
